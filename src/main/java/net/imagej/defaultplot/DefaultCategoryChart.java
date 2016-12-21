package net.imagej.defaultplot;

import net.imagej.plot.*;

import java.awt.*;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Matthias Arzt
 */
public class DefaultCategoryChart implements CategoryChart {

	private String title;

	private NumberAxis valueAxis;

	private CategoryAxis categoryAxis;

	private Collection<CategorySeries> items;

	DefaultCategoryChart() {
		valueAxis = new DefaultNumberAxis();
		categoryAxis = new DefaultCategoryAxis();
		items = new LinkedList<>();
	}

	@Override
	public SeriesStyle createSeriesStyle(Color color, LineStyle lineStyle, MarkerStyle markerStyle) {
		return new DefaultSeriesStyle(color, lineStyle, markerStyle);
	}

	@Override
	public LineSeries createLineSeries(String label, Collection<Double> values) {
		return new DefaultLineSeries(label, values);
	}

	@Override
	public BarSeries createBarSeries(String label, Collection<Double> values) {
		return new DefaultBarSeries(label, values);
	}

	@Override
	public NumberAxis getNumberAxis() {
		return valueAxis;
	}

	@Override
	public CategoryAxis getCategoryAxis() {
		return categoryAxis;
	}

	public Collection<CategorySeries> getItems() {
		return items;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}
}
