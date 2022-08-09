package kh.petmily.domain.abandoned_animal.form;

import lombok.Data;

import java.sql.Date;

@Data
public class VolunteerApplySubmitForm {

	// 신청서폼
	private int vaNumber;
	private int mNumber;
	private Date volunteerStartDay;
	private int volunteerPeriod;
	private int sNumber;

	public VolunteerApplySubmitForm(int mNumber, Date volunteerStartDay, int volunteerPeriod, int sNumber) {
		super();
		this.mNumber = mNumber;
		this.volunteerStartDay = volunteerStartDay;
		this.volunteerPeriod = volunteerPeriod;
		this.sNumber = sNumber;
	}

	public int getvaNumber() {
		return vaNumber;
	}

	public void setvaNumber(int vaNumber) {
		this.vaNumber = vaNumber;
	}

	public int getmNumber() {
		return mNumber;
	}

	public void setmNumber(int mNumber) {
		this.mNumber = mNumber;
	}

	public Date getVolunteerStartDay() {
		return volunteerStartDay;
	}

	public void setVolunteerStartDay(Date volunteerStartDay) {
		this.volunteerStartDay = volunteerStartDay;
	}

	public int getVolunteerPeriod() {
		return volunteerPeriod;
	}

	public void setVolunteerPeriod(int volunteerPeriod) {
		this.volunteerPeriod = volunteerPeriod;
	}

	public int getsNumber() {
		return sNumber;
	}

	public void setsNumber(int sNumber) {
		this.sNumber = sNumber;
	}

}
