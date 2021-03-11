package ${packageName};

<#list tableInfo.columnTypes as item>
    import ${item};
</#list>

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "${tableInfo.tableComment}")
public class ${tableInfo.className}DTO{

<#list tableInfo.columnInfos as item>
    <#if  item.attrShortType == "Date" >
        @ApiModelProperty(value="${item.comment}", example = "2020-01-01 00:00:00")
        private ${item.attrShortType} ${item.attrName};
    <#else>
        @ApiModelProperty("${item.comment}")
        <#if  item.field == "id" || item.field ?ends_with("Id")>
            private Long ${item.attrName};
        <#else>
            private ${item.attrShortType} ${item.attrName};
        </#if>
    </#if>

</#list>
}