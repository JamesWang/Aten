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
 * @author Jianjun Wang
 * 
 */
public class SingletonBytesPacketizerFactory implements PacketizerFactory {
    
    private int headerLength = 2;
    /**
     * A singleton instance of BytesPacketizer, it must be stateless, because it
     * is not thread-safe
     */
    private Packetizer instance ;
    
    public SingletonBytesPacketizerFactory(){
    }
    
    public SingletonBytesPacketizerFactory( int headerLength ){
        this.headerLength = headerLength;
        init();
    }

    public void setHeaderLength(int headerLength) {
        this.headerLength = headerLength;
    }

    @Override
    public Packetizer getPacketizer() {
        return instance;
    }

    @Override
    public synchronized void init() {
        if( instance == null ){
            instance = new BytePacketizer( headerLength );
        }
    }
}
