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

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

public class TestHashWithRandomSalt
{

	public static void main(String[] args)
	{
		String plainTextPassword = "foobar";

		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		AuthInfo authInfo = AuthInfoGenerator.generate(rng, plainTextPassword);

		System.out.println("salt: " + authInfo.getSalt());
		System.out.println("hash: " + authInfo.getHash());
	}

}
