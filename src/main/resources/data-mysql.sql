
delete from Facility_Order_Facilities;
delete from Facility_Services;
delete from Facility;
delete from Facility_Order;


delete from Service;
delete from User;

insert into Service(id, name, type)
values ('AmbSurServ', 'Ambulatory surgical services', 'FACILITY_TYPE');
insert into Service(id, name, type)
values ('BirthCenter', 'Birth center', 'FACILITY_TYPE');
insert into Service(id, name, type)
values ('BloodBank', 'Blood banks', 'FACILITY_TYPE');
insert into Service(id, name, type)
values ('ClinicsMedicalOffice', 'Clinics and medical offices', 'FACILITY_TYPE');
insert into Service(id, name, type)
values ('DiabetesEdCenter', 'Diabetes education centers', 'FACILITY_TYPE');
insert into Service(id, name, type)
values ('DialysisC', 'Dialysis Centers', 'FACILITY_TYPE');
insert into Service(id, name, type)
values ('HospiceH', 'Hospice homes', 'FACILITY_TYPE');
insert into Service(id, name, type)
values ('Hospital', 'Hospitals', 'FACILITY_TYPE');
insert into Service(id, name, type)
values ('ImagingRadiologyC', 'Imaging and radiology centers', 'FACILITY_TYPE');
insert into Service(id, name, type)
values ('MentalHealthT', 'Mental health and addiction treatment centers', 'FACILITY_TYPE');
insert into Service(id, name, type)
values ('NursingH', 'Nursing homes', 'FACILITY_TYPE');
insert into Service(id, name, type)
values ('OrthopedicRehabC', 'Orthopedic and other rehabilitation centers', 'FACILITY_TYPE');
insert into Service(id, name, type)
values ('TeleHealth', 'Telehealth', 'FACILITY_TYPE');
insert into Service(id, name, type)
values ('ImpatientCare', 'Inpatient care', 'MEDICAL_SERVICES');
insert into Service(id, name, type)
values ('UrgentCare', 'Urgent care', 'MEDICAL_SERVICES');
insert into Service(id, name, type)
values ('AmbulatoryCare', 'Ambulatory care', 'MEDICAL_SERVICES');

