package com.teracode.android.demo.model;

import com.teracode.android.common.util.ThreadUtil;
import com.teracode.android.framework.map.MapPoint;
import com.teracode.android.framework.map.SimpleMapPoint;
import com.teracode.android.framework.model.BaseMapModel;
import com.teracode.android.framework.model.ValueModel;
import com.teracode.android.framework.view.BaseView;

/**
 * @author Luciano Rey
 */
public class MapModel extends BaseMapModel<BaseView> {

	/**
	 * @param view
	 */
	public MapModel(BaseView view) {
		super(view);
	}

	/**
	 * @see com.teracode.android.framework.model.BaseModel#doInit(com.teracode.android.framework.model.ValueModel)
	 */
	@Override
	protected void doInit(ValueModel valueModel) {
		this.addMapPoint(new SimpleMapPoint("El Gato Negro", "Lugar clasico de la ciudad de Buenos Aires", -34.604309,
				-58.390389));
		this.addMapPoint(new SimpleMapPoint("Paseo La Plaza", "Lugar para ver espectaculos", -34.605284, -58.402156));
		ThreadUtil.sleep(3);
	}

	/**
	 * @see com.teracode.android.framework.model.BaseMapModel#handleElementSelected(com.teracode.android.framework.map.MapPoint)
	 */
	@Override
	protected void handleElementSelected(MapPoint mapPoint) {
		// TODO Auto-generated method stub
	}

}
