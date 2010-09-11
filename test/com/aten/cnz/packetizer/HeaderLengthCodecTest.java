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

import com.aten.cnz.packetizer.codec.HeaderLengthCodec;



/**
 * @author Jianjun Wang
 *
 */
public class HeaderLengthCodecTest {
    private HeaderLengthCodec codec = new HeaderLengthCodec(2);
    @Test
    public void encode() throws InvalidDataException{
	byte[] value = new byte[] {(byte)136};
	byte[] bs = codec.encode(136);		
	assertTrue( bs[0] == 0 );
	assertTrue( bs[1] == value[0] );
	
	value = new byte[]{(byte)3,(byte)135};
	bs = codec.encode(903);
	assertTrue( bs[0] == 3);
	assertTrue( bs[1] == (byte)135 );
    }
    @Test
    public void decode(){
	byte[] value = new byte[] {(byte)1, (byte)15};
	int len = 0;
	try {
	    len = codec.decode( value );
	} catch (InvalidDataException e) {
	    e.printStackTrace();
	}
	assertTrue( len ==  271);
    }
}
