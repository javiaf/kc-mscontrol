package com.tikal.mscontrol;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * This class is a Map of (Parameter=value) pairs. Each key identifies a
 * Parameter, and the Map provides its value.
 * <p>
 * <b>Note:</b> The value may be an instance of Value, for an enumerated
 * Parameter, or any other java Object, for a non-enumerated Parameter.<br>
 * This is indicated in each Parameter <b>documentation</b>.
 */
public interface Parameters extends Map<Parameter, Object> {

	/**
	 * A typed constant to use when no parameter is required.
	 */
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
		}
	};
}
