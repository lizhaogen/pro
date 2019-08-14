package com.qf.v2.product.web.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qf.v2.common.vo.ResultVO;
import com.qf.v2.product.web.vo.EditorIploadImgVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("common")
public class CommonsController {

    @Value("${image.server}")
    private String imageServer;

    @Autowired
    private FastFileStorageClient client;

    @RequestMapping("delImg")
    @ResponseBody
    public void delImg(String group,String path){
        client.deleteFile(group,path);
        String npath=  path.replaceAll("jpg", "_100x100.jpg");
        client.deleteFile(group,npath);

    }

    @RequestMapping(value = "editorUpload",method = RequestMethod.POST)
    @ResponseBody
    public EditorIploadImgVO editorIploadImgVO(MultipartFile file){
        StorePath storePath=getStorePathBUpload(file);
        EditorIploadImgVO vo = new EditorIploadImgVO();
        vo.setErrno(0);
        String path =imageServer+"/"+storePath.getFullPath();
        vo.setData(new String[]{path});
        return vo;
    }

    private StorePath getStorePathBUpload(MultipartFile file) {

            StorePath storePath = null;

            InputStream fis =null;

            String name ="";
            try {
                fis= file.getInputStream();

                name =file.getOriginalFilename();

                String fileExtname =name.substring(name.lastIndexOf(".")+1);

                storePath = client.uploadImageAndCrtThumbImage(fis, file.getSize(), fileExtname, null);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return storePath;
    }


    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO uploadImg(MultipartFile file){
        StorePath storePath = getStorePathBUpload(file);
        Logger logger = LoggerFactory.getLogger(CommonsController.class);

        Map<String, Object> map = new HashMap<>();
        map.put("group",storePath.getGroup());
        map.put("path",storePath.getPath());
        String fullpath=imageServer+"/"+storePath.getFullPath();
        logger.info(fullpath);
        map.put("fullpath",fullpath);
        return ResultVO.successResult(map);

    }

}
