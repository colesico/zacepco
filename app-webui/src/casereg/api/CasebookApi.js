import  * as restletUtils from "@common/assist/RestletUtils.js"; 

export async function listCasebooks(limit, offset){
	return await restletUtils.restletGet("/casereg/api/casebook",{limit,offset});
}

export async function createCasebook(file){
	const formData = new FormData();
	formData.append('script', file);
	
	return await restletUtils.restletPost("/casereg/api/casebook",formData,{});
}

export async function casebookOverview(casebookId){
	return await restletUtils.restletGet("/casereg/api/casebook/overview/"+casebookId);
}