package net.luis.xsurvive.data.provider.tag;

import net.luis.xsurvive.XSurvive;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class XSurviveBlockTagsProvider extends BlockTagsProvider {

	public XSurviveBlockTagsProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, XSurvive.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void addTags() {
		
	}
	
	@Override
	public String getName() {
		return "XSurvive Block Tags";
	}

}
