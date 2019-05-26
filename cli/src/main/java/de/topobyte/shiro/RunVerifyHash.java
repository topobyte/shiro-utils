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

import java.io.Console;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import de.topobyte.utilities.apache.commons.cli.OptionHelper;
import de.topobyte.utilities.apache.commons.cli.commands.args.CommonsCliArguments;
import de.topobyte.utilities.apache.commons.cli.commands.options.CommonsCliExeOptions;
import de.topobyte.utilities.apache.commons.cli.commands.options.ExeOptions;
import de.topobyte.utilities.apache.commons.cli.commands.options.ExeOptionsFactory;

public class RunVerifyHash
{

	private static final String OPTION_SALT = "salt";
	private static final String OPTION_HASH = "hash";

	public static ExeOptionsFactory OPTIONS_FACTORY = new ExeOptionsFactory() {

		@Override
		public ExeOptions createOptions()
		{
			Options options = new Options();
			// @formatter:off
            OptionHelper.addL(options, OPTION_SALT, true, true, "base64 salt", "a salt used for hashing");
            OptionHelper.addL(options, OPTION_HASH, true, true, "string", "a hashed password");
            // @formatter:on
			return new CommonsCliExeOptions(options, "[options]");
		}

	};

	public static void main(String name, CommonsCliArguments arguments)
			throws Exception
	{
		CommandLine line = arguments.getLine();

		String hash = line.getOptionValue(OPTION_HASH);
		String salt = line.getOptionValue(OPTION_SALT);

		Console console = System.console();

		char password[] = console.readPassword("Enter password: ");
		String pw = new String(password);

		VerifyHash task = new VerifyHash(salt, hash);
		task.execute(pw);
	}

}
