package com.wiserv.util.word;

import com.wiserv.util.util.IOUtil;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import java.io.File;
import java.io.OutputStream;

public class WordToPDFService {


    /**
     * word转化为pdf
     * @param docx
     * @param pdfPath
     * @throws Exception
     */
    public void docxToPDF(File docx, String pdfPath) throws Exception {
        OutputStream os = null;
        try {
            WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(docx);
            Mapper fontMapper = new IdentityPlusMapper();
            //中文字体转换
            fontMapper.getFontMappings().put("华文行楷", PhysicalFonts.getPhysicalFonts().get("STXingkai"));
            fontMapper.getFontMappings().put("隶书", PhysicalFonts.getPhysicalFonts().get("LiSu"));
            fontMapper.getFontMappings().put("宋体",PhysicalFonts.getPhysicalFonts().get("SimSun"));
            fontMapper.getFontMappings().put("微软雅黑",PhysicalFonts.getPhysicalFonts().get("Microsoft Yahei"));
            fontMapper.getFontMappings().put("黑体",PhysicalFonts.getPhysicalFonts().get("SimHei"));
            fontMapper.getFontMappings().put("楷体",PhysicalFonts.getPhysicalFonts().get("KaiTi"));
            fontMapper.getFontMappings().put("新宋体",PhysicalFonts.getPhysicalFonts().get("NSimSun"));
            fontMapper.getFontMappings().put("华文行楷", PhysicalFonts.getPhysicalFonts().get("STXingkai"));
            fontMapper.getFontMappings().put("华文仿宋", PhysicalFonts.getPhysicalFonts().get("STFangsong"));
            fontMapper.getFontMappings().put("宋体扩展",PhysicalFonts.getPhysicalFonts().get("simsun-extB"));
            fontMapper.getFontMappings().put("仿宋",PhysicalFonts.getPhysicalFonts().get("FangSong"));
            fontMapper.getFontMappings().put("仿宋_GB2312",PhysicalFonts.getPhysicalFonts().get("FangSong_GB2312"));
            fontMapper.getFontMappings().put("幼圆",PhysicalFonts.getPhysicalFonts().get("YouYuan"));
            fontMapper.getFontMappings().put("华文宋体",PhysicalFonts.getPhysicalFonts().get("STSong"));
            fontMapper.getFontMappings().put("华文中宋",PhysicalFonts.getPhysicalFonts().get("STZhongsong"));
            mlPackage.setFontMapper(fontMapper);

        /*    PdfConversion conversion = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(mlPackage);
            os = new FileOutputStream(pdfPath);

            conversion.output(os, new PdfSettings());*/
        } finally {
            IOUtil.quietClose(os);
        }
    }




}
