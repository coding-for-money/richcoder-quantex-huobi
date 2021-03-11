package ${packageName};

<#list tableInfo.columnTypes as item>
    import ${item};
</#list>

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@ApiModel(description = "${tableInfo.tableComment}修改信息")
public class ${tableInfo.className}ModifyDTO{

public interface Create {}
public interface Update extends Create {}

<#list tableInfo.columnInfos as item>
    <#if  item.field == tableInfo.primaryKey >
        @NotNull(groups = Update.class)
    <#elseif item.attrShortType == "String">
        @NotBlank(groups = Create.class)
    <#else>
        @NotNull(groups = Create.class)
    </#if>
    <#if  item.attrShortType == "Date" >
        @ApiModelProperty(value="${item.comment}", example = "2020-01-01 00:00:00")
    <#else>
        @ApiModelProperty("${item.comment}")
    </#if>
    <#if  item.field == "id" || item.field ?ends_with("Id")>
        private Long ${item.attrName};
    <#else>
        private ${item.attrShortType} ${item.attrName};
    </#if>

</#list>
}