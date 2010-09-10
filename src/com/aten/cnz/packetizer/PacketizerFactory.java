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
 * Create specific Packetizer
 * 
 * @author Jianjun Wang
 * 
 */
public interface PacketizerFactory {
    /**
     * Return a Packetizer instance or create one if it doesn't exist
     * @return An instance of Paketizer
     */
    Packetizer getPacketizer();

    /**
     * Initialize the PacketizerFactory
     */
    void init();
}
