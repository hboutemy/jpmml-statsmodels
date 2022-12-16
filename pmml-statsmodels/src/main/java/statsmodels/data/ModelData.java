/*
 * Copyright (c) 2020 Villu Ruusmann
 *
 * This file is part of JPMML-StatsModels
 *
 * JPMML-StatsModels is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JPMML-StatsModels is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with JPMML-StatsModels.  If not, see <http://www.gnu.org/licenses/>.
 */
package statsmodels.data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jpmml.python.PythonObject;

public class ModelData extends PythonObject {

	public ModelData(String module, String name){
		super(module, name);
	}

	public Cache getCache(){
		Map<String, ?> map = get("_cache", Map.class);

		Cache cache = new Cache(getPythonModule() + "." + getPythonName(), "_cache");
		cache.putAll(map);

		return cache;
	}

	public class Cache extends PythonObject {

		public Cache(String module, String name){
			super(module, name);
		}

		public List<String> getXNames(){
			return getList("xnames", String.class);
		}

		public List<String> getYNames(){
			Object ynames = get("ynames");

			if(ynames instanceof String){
				return Collections.singletonList((String)ynames);
			}

			return getList("ynames", String.class);
		}
	}
}