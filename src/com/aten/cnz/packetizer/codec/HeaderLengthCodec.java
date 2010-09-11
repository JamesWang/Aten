/** Copyright (c) 2010, Jianjun Wang <jooly.wang@gmail.com>
 *  All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 */

package com.aten.cnz.packetizer.codec;

import com.aten.cnz.packetizer.InvalidDataException;

/**
 * An encoder that prepends the length of the message. The length value is
 * prepended as a binary form. For example, <tt>{@link LengthFramer}</tt> will
 * encode the following 12-bytes string:
 * 
 * +----------------+
 * | "Good Morning" |
 * +----------------+
 * 
 * into the following:
 * 
 * +--------+----------------+
 * + 0x000C | "Good Morning" |
 * +--------+----------------+
 * 
 * @author Jianjun Wang
 * 
 */
public class HeaderLengthCodec {

    private int headerLength;

    public HeaderLengthCodec(int headerLength) {

	this.headerLength = headerLength;

	if (headerLength < 0) {
	    throw new IllegalArgumentException(
		    "HeaderLength must be a positive integer: " + headerLength);
	}
	if (headerLength != 1 && headerLength != 2 && headerLength != 3
		&& headerLength != 4 && headerLength != 8) {
	    throw new IllegalArgumentException(
		    "HeaderLength must be either 1, 2, 3, 4, or 8: "
			    + headerLength);
	}
    }

    /**
     * Parse the length stored in the header ( data )
     * 
     * @param data
     *            - byte array packet
     * @return Length of the data packet
     * @throws InvalidDataException
     *             if the length of the packet is not valid
     */
    public int decode(byte[] data) throws InvalidDataException {

	int len = data == null ? 0 : data.length;

	if (len >= headerLength) {
	    int total = 0;
	    for (int i = 0; i < headerLength; i++) {
		total = (total << 8) + (data[i] & 0xFF);
	    }
	    if (total > len) {
		return total;
	    }
	}
	throw new InvalidDataException("Data is too short");
    }

    /**
     * Encode the length and stored in the header ( data )
     * 
     * @param length
     *            - length of the packet ( header excluded )
     * @return length stored in byte[]
     * @throws
     */
    public byte[] encode(int length) {
	switch (headerLength) {
	case 1:
	    if (length > 0xFF) {
		invalidLength(length);
	    }
	    break;
	case 2:
	    if (length > 0xFFFF) {
		invalidLength(length);
	    }
	    break;
	case 3:
	    if (length > 0xFFFFFF) {
		invalidLength(length);
	    }
	    break;
	case 4:
	case 8:
	    break;
	default:
	    throw new Error("should not reach here");
	}

	int size = length;
	byte[] header = new byte[headerLength];
	for (int i = headerLength - 1; i >= 0; i--) {
	    header[i] = (byte) (size % 256);
	    size /= 256;
	}
	return header;
    }

    private void invalidLength(int length) {
	throw new IllegalArgumentException("length does not fit into a byte: "
		+ length);
    }
}
