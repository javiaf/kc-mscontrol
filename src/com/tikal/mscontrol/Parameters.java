package com.tikal.mscontrol;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface Parameters extends Map<Parameter, Object> {
	
	public static final Parameters NO_PARAMETER = new Parameters() {

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean containsKey(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean containsValue(Object arg0) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Set<java.util.Map.Entry<Parameter, Object>> entrySet() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object get(Object arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Set<Parameter> keySet() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object put(Parameter arg0, Object arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void putAll(Map<? extends Parameter, ? extends Object> arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object remove(Object arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Collection<Object> values() {
			// TODO Auto-generated method stub
			return null;
		}};
}
