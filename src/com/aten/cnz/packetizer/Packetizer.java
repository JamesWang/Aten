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

/**
 * @author Jian jun Wang
 * 
 */
public interface Packetizer {
    /**
     * Read data from the InputStream( in ), if no data available, it will be
     * blocked until timeout and a SocketTimeoutException will be thrown. If the
     * data read from the InputStream is not valid or cannot read enough data,
     * an InvalidpacketDataException will be thrown.
     * 
     * @param in  The source InputStream will be read from
     * @return The data read from the InputStream ( in )
     * 
     * @throws IOException
     * @throws SocketTimeoutException
     * @throws InvalidPacketDataException
     */
    byte[] readPacket(InputStream in) throws IOException,
            SocketTimeoutException, InvalidPacketDataException;

    /**
     * Write out the binary data into the OutputStream.
     * 
     * @param data  The binary data to be write
     * @param out   The destination OutputStream
     * 
     * @throws IOException
     */
    void writePacket(byte[] data, OutputStream out) throws IOException;

    /**
     * Cleanup the Packetizer if necessary, depends on the implementation
     */
    void close();
}
