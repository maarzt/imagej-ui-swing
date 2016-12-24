package net.imagej.ui.swing.viewer.plot.gral;

import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Drawable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.graphics.Label;
import de.erichseifert.gral.plots.axes.Axis;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.axes.LinearRenderer2D;
import de.erichseifert.gral.plots.axes.LogarithmicRenderer2D;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import net.imagej.plot.*;
import net.imagej.plot.XYPlot;
import net.imagej.ui.swing.viewer.plot.utils.AwtLineStyles;
import net.imagej.ui.swing.viewer.plot.utils.AwtMarkerStyles;
import org.scijava.ui.awt.AWTColors;

import java.awt.Color;
import java.awt.BasicStroke;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Matthias Arzt
 */
class GralXYPlotGenerator {

	private XYPlot plot;

	private de.erichseifert.gral.plots.XYPlot gralPlot;

	private GralXYPlotGenerator(XYPlot plot) {
		this.plot = plot;
	}

	private Drawable getGralChart() {
		gralPlot = new de.erichseifert.gral.plots.XYPlot();
		addAllSeries();


		gralPlot.setInsets(new Insets2D.Double( 10, 40, 40, 10));
		gralPlot.getTitle().setText(replaceNull(plot.getTitle(), ""));
		gralPlot.setLegendVisible(true);

		// Style the gralPlot area
		//gralPlot.getPlotArea().setBorderStroke(new BasicStroke(2f));

		// Style axes
		setAxis(de.erichseifert.gral.plots.XYPlot.AXIS_X, plot.getXAxis());
		setAxis(de.erichseifert.gral.plots.XYPlot.AXIS_Y, plot.getYAxis());
		return gralPlot;
	}

	private void addAllSeries() {
		for(XYPlotItem series : plot.getItems())
			if(series instanceof XYSeries)
				addXYSeries((XYSeries) series);
	}

	private void addXYSeries(XYSeries series) {
		DataSeries gralSeries = addGralDataSeries(series.getLabel(), series.getXValues(), series.getYValues());
		addGralRenderer(gralSeries, series.getStyle());
	}

	private DataSeries addGralDataSeries(String label, Collection<Double> xs, Collection<Double> ys) {
		DataTable data = new DataTable(Double.class, Double.class);
		Iterator<Double> xi = xs.iterator();
		Iterator<Double> yi = ys.iterator();
		while(xi.hasNext() && yi.hasNext())
			data.add(xi.next(), yi.next());
		DataSeries series =  new DataSeries(label, data, 0, 1);
		gralPlot.add(series);
		return series;
	}

	private void addGralRenderer(DataSeries series, SeriesStyle style) {
		if(style == null)
			return;
		// Style data series
		Color color = AWTColors.getColor(style.getColor());
		AwtLineStyles line = AwtLineStyles.getInstance(style.getLineStyle());
		if(line.isVisible()) {
			LineRenderer renderer = new DefaultLineRenderer2D();
			renderer.setColor(color);
			renderer.setStroke(line.getStroke());
			gralPlot.setLineRenderers(series, renderer);
		}

		AwtMarkerStyles marker = AwtMarkerStyles.getInstance(style.getMarkerStyle());
		PointRenderer pointRenderer = new DefaultPointRenderer2D();
		pointRenderer.setColor(color);
		pointRenderer.setShape(marker.getShape());
		gralPlot.setPointRenderers(series, pointRenderer);
	}

	private void setAxis(String gralAxisId, NumberAxis axis) {
		Axis a = gralPlot.getAxis(gralAxisId);
		a.setAutoscaled(axis.getRangeStrategy() != RangeStrategy.MANUAL);
		if(axis.getRangeStrategy() == RangeStrategy.MANUAL)
			a.setRange(axis.getMin(), axis.getMax());
		AxisRenderer r = axis.isLogarithmic() ?
				new LogarithmicRenderer2D() :
				new LinearRenderer2D();
		r.setLabel(new Label(replaceNull(axis.getLabel(), "")));
		r.setIntersection(-Double.MAX_VALUE);
		gralPlot.setAxisRenderer(gralAxisId, r);
	}

	private static <T> T replaceNull(T value, T replace) {
		return (value != null) ? value : replace;
	}

	static public Drawable generate(XYPlot plot) {
		return new GralXYPlotGenerator(plot).getGralChart();
	}
}
