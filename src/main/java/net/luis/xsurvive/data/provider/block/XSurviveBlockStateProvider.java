package net.luis.xsurvive.data.provider.block;

import net.luis.xsurvive.XSurvive;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class XSurviveBlockStateProvider extends BlockStateProvider {

	protected final ExistingFileHelper existingFileHelper;
	
	public XSurviveBlockStateProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, XSurvive.MOD_ID, existingFileHelper);
		this.existingFileHelper = existingFileHelper;
	}
	
	@Override
	protected void registerStatesAndModels() {
		
	}
	
	@Override
	public String getName() {
		return "XSurvive Block States";
	}
	
}

