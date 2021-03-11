package ${packageName};

import com.richcoder.common.util.vo.Pageable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "${tableInfo.tableComment}分页条件信息")
public class ${tableInfo.className}PageableDTO extends Pageable{

<#--<#list tableInfo.columnInfos as item>
<#if  item.attrShortType == "Date" >
     @ApiModelProperty(value="${item.comment}", example = "2020-01-01 00:00:00")
<#else>
     @ApiModelProperty("${item.comment}")
</#if>
     private ${item.attrShortType} ${item.attrName};
</#list>-->
}