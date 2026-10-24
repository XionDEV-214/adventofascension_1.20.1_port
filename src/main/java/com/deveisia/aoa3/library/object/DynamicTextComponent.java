package com.deveisia.aoa3.library.object;

import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

import java.util.Optional;
import java.util.function.Supplier;

public final class DynamicTextComponent implements ComponentContents {
	private final Supplier<String> text;

	private DynamicTextComponent(Supplier<String> text) {
		this.text = text;
	}

	public static MutableComponent create(Supplier<String> text) {
		return MutableComponent.create(new DynamicTextComponent(text));
	}

	@Override
	public <T> Optional<T> visit(FormattedText.ContentConsumer<T> consumer) {
		return consumer.accept(this.text.get());
	}

	@Override
	public <T> Optional<T> visit(FormattedText.StyledContentConsumer<T> consumer, Style style) {
		return consumer.accept(style, this.text.get());
	}

	@Override
	public boolean equals(Object other) {
		return this == other || (other instanceof DynamicTextComponent otherComponent && this.text.get().equals(otherComponent.text.get()));
	}

	@Override
	public int hashCode() {
		return this.text.get().hashCode();
	}

	@Override
	public String toString() {
		return "dynamic{" + this.text.get() + "}";
	}
}
