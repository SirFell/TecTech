package com.github.technus.tectech.thing.metaTileEntity.hatch;

import com.github.technus.tectech.CommonValues;
import com.github.technus.tectech.thing.machineTT;
import com.github.technus.tectech.thing.metaTileEntity.hatch.gui.GT_Container_Holder;
import com.github.technus.tectech.thing.metaTileEntity.hatch.gui.GT_GUIContainer_Holder;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.objects.GT_RenderedTexture;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

/**
 * Created by Tec on 03.04.2017.
 */
public class GT_MetaTileEntity_Hatch_Holder extends GT_MetaTileEntity_Hatch implements machineTT {
    private static Textures.BlockIcons.CustomIcon EM_H;
    private static Textures.BlockIcons.CustomIcon EM_H_ACTIVE;

    public GT_MetaTileEntity_Hatch_Holder(int aID, String aName, String aNameRegional, int aTier, String descr) {
        super(aID, aName, aNameRegional, aTier, 1, descr);
    }

    public GT_MetaTileEntity_Hatch_Holder(String aName, int aTier, String aDescription, ITexture[][][] aTextures) {
        super(aName, aTier, 1, aDescription, aTextures);
    }

    @Override
    public void registerIcons(IIconRegister aBlockIconRegister) {
        super.registerIcons(aBlockIconRegister);
        EM_H_ACTIVE = new Textures.BlockIcons.CustomIcon("iconsets/EM_HOLDER_ACTIVE");
        EM_H = new Textures.BlockIcons.CustomIcon("iconsets/EM_HOLDER");
    }

    @Override
    public ITexture[] getTexturesActive(ITexture aBaseTexture) {
        return new ITexture[]{aBaseTexture, new GT_RenderedTexture(EM_H_ACTIVE)};
    }

    @Override
    public ITexture[] getTexturesInactive(ITexture aBaseTexture) {
        return new ITexture[]{aBaseTexture, new GT_RenderedTexture(EM_H)};
    }

    @Override
    public MetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
        return new GT_MetaTileEntity_Hatch_Holder(mName, mTier, mDescription, mTextures);
    }

    @Override
    public boolean isSimpleMachine() {
        return true;
    }

    @Override
    public boolean isFacingValid(byte aFacing) {
        return aFacing >= 2;
    }

    @Override
    public boolean isAccessAllowed(EntityPlayer aPlayer) {
        return true;
    }

    @Override
    public boolean isValidSlot(int aIndex) {
        return true;
    }

    @Override
    public boolean allowPullStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, byte aSide, ItemStack aStack) {
        return aSide == aBaseMetaTileEntity.getFrontFacing();
    }

    @Override
    public boolean allowPutStack(IGregTechTileEntity aBaseMetaTileEntity, int aIndex, byte aSide, ItemStack aStack) {
        return aSide == aBaseMetaTileEntity.getFrontFacing();
    }

    @Override
    public Object getServerGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_Container_Holder(aPlayerInventory, aBaseMetaTileEntity);
    }

    @Override
    public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
        return new GT_GUIContainer_Holder(aPlayerInventory, aBaseMetaTileEntity, "Holder Hatch");
    }

    @Override
    public boolean onRightclick(IGregTechTileEntity aBaseMetaTileEntity, EntityPlayer aPlayer) {
        if (aBaseMetaTileEntity.isClientSide()) return true;
        //if(aBaseMetaTileEntity.isActive())
        //    aPlayer.addChatComponentMessage(new ChatComponentText("It is still active..."));
        //else if(heat>0)
        //    aPlayer.addChatComponentMessage(new ChatComponentText("It is still warm..."));
        //else
        aBaseMetaTileEntity.openGUI(aPlayer);
        return true;
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    //@Override
    //public void onRemoval() {
    //    if(mInventory!=null && (heat>0 || (getBaseMetaTileEntity()!=null && getBaseMetaTileEntity().isActive())))
    //        for(int i=0;i<mInventory.length;i++)
    //            mInventory[i]=null;
    //}

    @Override
    public String[] getDescription() {
        return new String[]{
                CommonValues.tecMark,
                mDescription,
                EnumChatFormatting.AQUA + "Advanced Holding Mechanism!"
        };
    }
}

