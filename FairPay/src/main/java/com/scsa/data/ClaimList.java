package com.scsa.data;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import com.scsa.model.vo.ClaimInfo;

@XmlRootElement
public class ClaimList {

	private List<ClaimInfo> claimList;

	public List<ClaimInfo> getUserList() {
		return claimList;
	}

	@XmlElement(name="claim")
	public void setClaimList(List<ClaimInfo> claimList) {
		this.claimList = claimList;
	}
	
}
