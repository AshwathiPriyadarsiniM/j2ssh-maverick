/**
 * Copyright 2003-2016 SSHTOOLS Limited. All Rights Reserved.
 *
 * For product documentation visit https://www.sshtools.com/
 *
 * This file is part of J2SSH Maverick.
 *
 * J2SSH Maverick is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * J2SSH Maverick is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with J2SSH Maverick.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sshtools.util;

import java.io.ByteArrayOutputStream;

/**
 * 
 * 
 * @author $author$
 */
public class SimpleASNWriter {
	private ByteArrayOutputStream data;

	/**
	 * Creates a new SimpleASNWriter object.
	 */
	public SimpleASNWriter() {
		this.data = new ByteArrayOutputStream();
	}

	/**
	 * 
	 * 
	 * @param b
	 */
	public void writeByte(int b) {
		data.write(b);
	}

	/**
	 * 
	 * 
	 * @param b
	 */
	public void writeData(byte[] b) {
		writeLength(b.length);
		this.data.write(b, 0, b.length);
	}

	/**
	 * 
	 * 
	 * @param length
	 */
	public void writeLength(int length) {
		if (length < 0x80) {
			data.write(length);
		} else {
			if (length < 0x100) {
				data.write(0x81);
				data.write(length);
			} else if (length < 0x10000) {
				data.write(0x82);
				data.write(length >>> 8);
				data.write(length);
			} else if (length < 0x1000000) {
				data.write(0x83);
				data.write(length >>> 16);
				data.write(length >>> 8);
				data.write(length);
			} else {
				data.write(0x84);
				data.write(length >>> 24);
				data.write(length >>> 16);
				data.write(length >>> 8);
				data.write(length);
			}
		}
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public byte[] toByteArray() {
		return data.toByteArray();
	}
}
