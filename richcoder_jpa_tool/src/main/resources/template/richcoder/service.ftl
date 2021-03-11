package ${packageName};

import com.richcoder.common.spring.jpa.base.BaseService;
import com.richcoder.common.util.vo.Page;
import com.richcoder.common.util.vo.Pageable;
import ${config.dtoPackageName}.request.${tableInfo.classPackageName}.${tableInfo.className}PageableDTO;
import ${config.dtoPackageName}.response.${tableInfo.classPackageName}.${tableInfo.className}DTO;
import ${config.modelPackageName}.${tableInfo.className};

/**
* ${tableInfo.tableComment} service接口
*/
public interface ${tableInfo.className}Service extends BaseService<${tableInfo.className}>{

Page
<${tableInfo.className}DTO> page(${tableInfo.className}PageableDTO dto);
    }