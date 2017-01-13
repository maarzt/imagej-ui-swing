package net.imagej.defaultplot;

import net.imagej.plot.BoxSeries;
import org.scijava.util.ColorRGB;

import java.util.Collection;
import java.util.Map;

/**
 * @author Matthias Arzt
 */
public class DefaultBoxSeries<C> implements BoxSeries<C> {

	private String label = null;

	private ColorRGB color = null;

	private Map<C, Collection<Double>> values = null;

	private boolean legendVisible = true;

	public DefaultBoxSeries() { }

	@Override
	public Map<C, Collection<Double>> getValues() {
		return values;
	}

	@Override
	public void setValues(Map<C, Collection<Double>> values) {
		this.values = values;
	}

	@Override
	public ColorRGB getColor() {
		return color;
	}

	@Override
	public void setColor(ColorRGB color) {
		this.color = color;
	}

	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public boolean getLegendVisible() {
		return legendVisible;
	}

	@Override
	public void setLegendVisible(boolean visible) {
		legendVisible = visible;
	}
}
