package net.imagej.plot;

import java.util.Collection;

/**
 * @author Matthias Arzt
 */
public interface LineSeries extends LabeledObject {

	Collection<Double> getValues();

	void setValues(Collection<Double> Values);

	SeriesStyle getStyle();

	void setStyle(SeriesStyle style);

}
