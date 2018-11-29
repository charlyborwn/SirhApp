/*
 * Copyright 2009-2011 Prime Technology.
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
package org.primefaces.ultima.view;

import java.io.Serializable;

import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

public class ViewparamPushBean implements Serializable{

	private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

	public void prerender() {
		PushContext pushContext = PushContextFactory.getDefault().getPushContext();
        
        pushContext.push("/viewparam", data);
	}
}