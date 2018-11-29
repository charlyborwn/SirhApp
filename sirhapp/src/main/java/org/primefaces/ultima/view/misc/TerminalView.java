/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.ultima.view.misc;

import java.util.Date;
import javax.inject.Named;

@Named
public class TerminalView {
    
    public String handleCommand(String command, String[] params) {
		if(command.equals("greet")) {
            if(params.length > 0)
                return "Hello " + params[0];
            else
                return "Hello Stranger";
        }
		else if(command.equals("date"))
			return new Date().toString();
		else
			return command + " not found";
	}
}