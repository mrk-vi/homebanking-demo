package uni.mirkoz.homebankingdemo.controller.rest.bankmanager.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import uni.mirkoz.homebankingdemo.model.banks.SqlTimeDeserializer;

import java.sql.Time;

@Data
public class CreateBankBranchForm {
    private String bankBranchName;
    private String bankBranchAddress;
    @JsonFormat(pattern = "HH:mm")
    @ApiModelProperty(dataType = "java.lang.String", example = "08:00")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    private Time bankBranchOpening;
    @JsonFormat(pattern = "HH:mm")
    @ApiModelProperty(dataType = "java.lang.String", example = "19:00")
    @JsonDeserialize(using = SqlTimeDeserializer.class)
    private Time bankBranchClosing;
    // TODO handling images uploads
}
