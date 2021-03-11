package ${packageName};

import com.richcoder.common.spring.jpa.base.BaseDao;
import com.richcoder.common.spring.jpa.base.impl.BaseServiceImpl;
import com.richcoder.common.util.vo.Page;
import com.richcoder.common.util.vo.Pageable;
import ${config.dtoPackageName}.request.${tableInfo.classPackageName}.${tableInfo.className}PageableDTO;
import ${config.dtoPackageName}.response.${tableInfo.classPackageName}.${tableInfo.className}DTO;
import ${config.daoPackageName}.${tableInfo.className}Dao;
import ${config.modelPackageName}.${tableInfo.className};
import ${config.servicePackageName}.${tableInfo.className}Service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
* ${tableInfo.tableComment} service接口实现类
*/
@Service("${tableInfo.shortStartClassName}Service")
public class ${tableInfo.className}ServiceImpl extends BaseServiceImpl<${tableInfo.className}> implements ${tableInfo.className}Service {

private static final Logger log = LoggerFactory.getLogger(${tableInfo.className}ServiceImpl.class);

@Resource(name="${tableInfo.shortStartClassName}Dao")
private ${tableInfo.className}Dao ${tableInfo.shortStartClassName}Dao;

@Override
protected BaseDao<${tableInfo.className}> getDao(){
return ${tableInfo.shortStartClassName}Dao;
}

@Override
public Page
<${tableInfo.className}DTO> page(${tableInfo.className}PageableDTO dto) {
    StringBuilder sb = new StringBuilder();
    Map
    <String
            , Object> map = new HashMap<>();
        sb.append("select <#list tableInfo.columnInfos as item>${item.field}<#if item_has_next>,</#if></#list>
        from ${tableInfo.tableName} ");
        return ${tableInfo.shortStartClassName}Dao.nativeFindPage(sb.toString(),
        map, ${tableInfo.className}DTO.class, dto);
        }
        }