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
package com.aten.cnz.packetizer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;

import com.aten.cnz.packetizer.codec.HeaderLengthCodec;

/**
 * Read/Write packets with n-bytes header from/to InputStream/OutputStream.
 * Normally, it will be used in TCP/IP socket's Input/Output Stream in blocking
 * I/O mode
 * 
 * @author Jianjun Wang
 * 
 */

public class BytePacketizer implements Packetizer {
    /**
     * The number of bytes prepended to the original data, default will prepend
     * 2 bytes, but can be configured to 1,2,3,4,8
     */
    private int headerLength = 2;
    /**
     * n-byte header encoder/decoder, which will convert an integer number of
     * bytes header into a binary format or vice-versa
     * 
     */
    private HeaderLengthCodec codec;

    public BytePacketizer() {
	init();
    }

    public BytePacketizer(int headerLength) {
	this.headerLength = headerLength;
	init();
    }

    /**
     * Read transaction data from InputStream in blocking mode with timeout
     * 
     * @param InputStream
     * 
     * @return data in byte array
     * @throws SocketTimeoutException
     *             if no data returned after timeout happends
     *             InvaildPacketDataException if the header is not proper
     */
    @Override
    public byte[] readPacket(InputStream in) throws IOException,
	    SocketTimeoutException, InvalidDataException {

	byte[] header = new byte[headerLength];

	int size = in.read(header, 0, headerLength);

	if (size < 0 || size < headerLength) {
	    throw new IOException("InputStream Closed");
	}

	int len = codec.decode(header);
	byte[] data = new byte[len];
	size = in.read(data, 0, len);
	if (size < 0 || size < len) {
	    throw new IOException("InputStream Closed");
	}
	return data;
    }

    /**
     * Write transaction data from InputStream in blocking mode
     * 
     * @param data   to be written out
     * @param OutputStream
     * @return
     * @throws IOException if write to OutputStream failed
     */
    @Override
    public void writePacket(byte[] data, OutputStream out) throws IOException {

	if (data == null || data.length == 0) {
	    throw new IOException("Invalid Data");
	}

	byte[] header = codec.encode(data.length);

	out.write(header);
	out.write(data);
	out.flush();
    }

    /**
     * Set the headder length in n-byte format
     * 
     * @param headerLength
     */
    public void setHeaderLength(int headerLength) {
	this.headerLength = headerLength;
	init();
    }

    @Override
    public void close() {
	//TODO
    }

    private void init() {
	codec = new HeaderLengthCodec(headerLength);
    }
}
