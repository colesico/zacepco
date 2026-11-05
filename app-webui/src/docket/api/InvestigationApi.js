import  * as restletUtils from "@core/assist/RestletUtils.js"; 

export async function createInvestigationEntry(request){
	return await restletUtils.restletPost("/docket/api/investigation",request,{});
}

export async function findInvestigationEntry(id){
	return await restletUtils.restletGet("/docket/api/investigation/"+id);
}

