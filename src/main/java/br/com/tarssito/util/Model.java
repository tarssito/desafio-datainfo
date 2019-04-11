package br.com.tarssito.util;

import java.io.Serializable;

public interface Model<ID extends Serializable> extends Serializable {

	ID getId();
}
