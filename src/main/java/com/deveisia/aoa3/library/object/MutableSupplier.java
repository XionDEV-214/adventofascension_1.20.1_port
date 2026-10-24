package com.deveisia.aoa3.library.object;

import java.util.function.Supplier;
import javax.annotation.Nullable;

public class MutableSupplier<T> implements Supplier<T> {
	private Supplier<T> supplier;

	public MutableSupplier (@Nullable Supplier<T> supplier) {
		this.supplier = supplier;
	}

	public void update(@Nullable Supplier<T> supplier) {
		this.supplier = supplier;
	}

	@Nullable
	@Override
	public T get() {
		return supplier.get();
	}
}
