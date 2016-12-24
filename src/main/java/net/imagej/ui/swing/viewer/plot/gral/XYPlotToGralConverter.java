package net.imagej.ui.swing.viewer.plot.gral;

import de.erichseifert.gral.graphics.Drawable;
import net.imagej.plot.XYPlot;
import org.scijava.Priority;
import org.scijava.convert.AbstractConverter;
import org.scijava.convert.Converter;
import org.scijava.plugin.Plugin;

/**
 * @author Matthias.Arzt
 */
@Plugin(type = Converter.class, priority = Priority.NORMAL_PRIORITY)
public class XYPlotToGralConverter  extends AbstractConverter<XYPlot, Drawable> {
	@SuppressWarnings("unchecked")
	@Override
	public <T> T convert(Object o, Class<T> aClass) {
		return (T) GralXYPlotGenerator.generate((XYPlot) o);
	}

	@Override
	public Class<Drawable> getOutputType() {
		return Drawable.class;
	}

	@Override
	public Class<XYPlot> getInputType() {
		return XYPlot.class;
	}
}
