import  * as restletUtils from "@common/assist/RestletUtils.js"; 

export async function listCaseFiles(limit, offset){
	return await restletUtils.restletGet("/api/cases/case-file",{limit,offset});
}

export async function createCaseFile(file){
	const formData = new FormData();
	formData.append('script', file);
	
	return await restletUtils.restletPost("/api/cases/case-file",formData,{});
}