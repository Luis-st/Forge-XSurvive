package net.luis.xsurvive.data.provider.item;

import net.luis.xsurvive.XSurvive;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class XSurviveItemModelProvider extends ItemModelProvider {

	public XSurviveItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, XSurvive.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		
	}
	
	@Override
	public String getName() {
		return "XSurvive Item Models";
	}

}
