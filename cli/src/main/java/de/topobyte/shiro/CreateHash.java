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

public class CreateHash
{

	private String salt = null;

	public void setSalt(String salt)
	{
		this.salt = salt;
	}

	public void execute(String plainTextPassword)
	{
		AuthInfo authInfo;
		if (salt == null) {
			RandomNumberGenerator rng = new SecureRandomNumberGenerator();
			authInfo = AuthInfoGenerator.generate(rng, plainTextPassword);
		} else {
			authInfo = AuthInfoGenerator.generate(plainTextPassword, salt);
		}

		System.out.println("salt: " + authInfo.getSalt());
		System.out.println("hash: " + authInfo.getHash());
	}

}
