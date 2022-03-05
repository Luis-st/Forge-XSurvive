package net.luis.xsurvive.data.provider.tag;

import net.luis.xsurvive.XSurvive;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class XSurviveItemTagsProvider extends ItemTagsProvider {

	public XSurviveItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper) {
		super(generator, blockTagsProvider, XSurvive.MOD_ID, existingFileHelper);
	}
	
	@Override
	protected void addTags() {
		
	}

	@Override
	public String getName() {
		return "XSurvive Item Tags";
	}
	
}

