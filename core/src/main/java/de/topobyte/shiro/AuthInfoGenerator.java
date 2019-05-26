// Copyright 2019 Sebastian Kuerten
//
// This file is part of shiro-utils.
//
// shiro-utils is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// shiro-utils is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with shiro-utils. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;

public class AuthInfoGenerator
{

	public static AuthInfo generate(RandomNumberGenerator rng,
			String plainTextPassword)
	{
		ByteSource bs = rng.nextBytes();

		byte[] bytes = bs.getBytes();
		String salt64 = bs.toBase64();

		bytes = Base64.decode(salt64);

		return generate(plainTextPassword, bytes);
	}

	public static AuthInfo generate(String plainTextPassword, String salt)
	{
		byte[] bytes = Base64.decode(salt);
		return generate(plainTextPassword, bytes);
	}

	private static AuthInfo generate(String plainTextPassword, byte[] bytes)
	{
		String hashedPassword64 = new Sha256Hash(plainTextPassword, bytes, 1024)
				.toBase64();

		String salt64 = Base64.encodeToString(bytes);

		return new AuthInfo(hashedPassword64, salt64);
	}

}
