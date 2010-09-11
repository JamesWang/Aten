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

/**
 * When the data read by the Packetizer is invalid, for example, the
 * BytesPacketizer will throw this exception if it read invalid prepended header
 * 
 * @author Jianjun Wang
 * 
 */
public class InvalidDataException extends Exception {

    private static final long serialVersionUID = -581355395751763544L;

    public InvalidDataException() {
    }

    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException(Exception e) {
        super(e);
    }
}
