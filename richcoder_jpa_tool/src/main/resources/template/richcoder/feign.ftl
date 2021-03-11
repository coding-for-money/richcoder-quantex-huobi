package ${packageName};

import com.richcoder.common.util.constant.ServiceProvider;
import com.richcoder.common.util.vo.IdVO;
import com.richcoder.common.util.vo.Page;
import com.richcoder.common.util.vo.Pageable;
import ${config.dtoPackageName}.request.${tableInfo.classPackageName}.${tableInfo.className}ModifyDTO;
import ${config.dtoPackageName}.request.${tableInfo.classPackageName}.${tableInfo.className}PageableDTO;
import ${config.dtoPackageName}.response.${tableInfo.classPackageName}.${tableInfo.className}DTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
* ${tableInfo.tableComment} 远程服务
*/
@FeignClient(name = ServiceProvider.PRODUCT_SERVICE)
public interface Remote${tableInfo.className}Service {

@PostMapping("/${tableInfo.shortStartClassName}/page")
Page
<${tableInfo.className}DTO> page( @RequestBody ${tableInfo.className}PageableDTO dto);

    @PostMapping("/${tableInfo.shortStartClassName}/get")
    ${tableInfo.className}DTO getOne( @RequestBody IdVO dto);

    @PostMapping("/${tableInfo.shortStartClassName}/add")
    Long add( @RequestBody ${tableInfo.className}ModifyDTO dto);

    @PostMapping("/${tableInfo.shortStartClassName}/update")
    void update( @RequestBody ${tableInfo.className}ModifyDTO dto);

    @PostMapping("/${tableInfo.shortStartClassName}/delete")
    void delete(@RequestBody IdVO dto);
    }