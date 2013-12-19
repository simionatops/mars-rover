package br.com.zup.rover.core.model;

public enum DirectionType {

	N {
		@Override
		public DirectionType turnRight() {
			return E;
		}

		@Override
		public DirectionType turnleft() {
			return W;
		}
	},

	E {
		@Override
		public DirectionType turnRight() {
			return S;
		}

		@Override
		public DirectionType turnleft() {
			return N;
		}
	},

	S {
		@Override
		public DirectionType turnRight() {
			return W;
		}

		@Override
		public DirectionType turnleft() {
			return E;
		}
	},

	W {
		@Override
		public DirectionType turnRight() {
			return N;
		}

		@Override
		public DirectionType turnleft() {
			return S;
		}
	};

	public abstract DirectionType turnRight();

	public abstract DirectionType turnleft();
}
