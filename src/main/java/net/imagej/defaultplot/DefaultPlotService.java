
package net.imagej.defaultplot;

import net.imagej.plot.CategoryChart;
import net.imagej.plot.PlotService;
import net.imagej.plot.XYPlot;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.Service;

/**
 * The default implementation of the {@link PlotService} interface.
 *
 * @author Matthias Arzt
 */

@Plugin(type = Service.class)
public class DefaultPlotService extends AbstractService implements PlotService {

	// -- PlotService methods --
	
	@Override
	public XYPlot newXYPlot() {
		return new DefaultXYPlot();
	}

	@Override
	public <C> CategoryChart<C> newCategoryChart(Class<C> categoryType) {
		return new DefaultCategoryChart<C>(categoryType);
	}

}
