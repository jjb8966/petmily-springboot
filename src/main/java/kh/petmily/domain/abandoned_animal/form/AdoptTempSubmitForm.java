package kh.petmily.domain.abandoned_animal.form;

import lombok.Data;

import java.sql.Date;

@Data
public class AdoptTempSubmitForm {

	private int adNumber; // 입양 번호
	private int mNumber;
	private int abNumber;
	private String residence;
	private String maritalStatus;
	private String job;
	private String status = "처리중";

	private int tNumber; // 임보 번호
	private Date tempDate;
	private int tempPeriod;

	public AdoptTempSubmitForm(int mNumber, int abNumber, String residence, String maritalStatus, String job) {
		this.mNumber = mNumber;
		this.abNumber = abNumber;
		this.residence = residence;
		this.maritalStatus = maritalStatus;
		this.job = job;
	}

	public int getAdNumber() {
		return adNumber;
	}

	public void setAdNumber(int adNumber) {
		this.adNumber = adNumber;
	}

	public int getmNumber() {
		return mNumber;
	}

	public void setmNumber(int mNumber) {
		this.mNumber = mNumber;
	}

	public int getAbNumber() {
		return abNumber;
	}

	public void setAbNumber(int abNumber) {
		this.abNumber = abNumber;
	}

	public String getResidence() {
		return residence;
	}

	public void setResidence(String residence) {
		this.residence = residence;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int gettNumber() {
		return tNumber;
	}

	public void settNumber(int tNumber) {
		this.tNumber = tNumber;
	}

	public Date getTempDate() {
		return tempDate;
	}

	public void setTempDate(Date tempDate) {
		this.tempDate = tempDate;
	}

	public int getTempPeriod() {
		return tempPeriod;
	}

	public void setTempPeriod(int tempPeriod) {
		this.tempPeriod = tempPeriod;
	}
}
