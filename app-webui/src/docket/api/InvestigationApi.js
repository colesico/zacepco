import  * as restletUtils from "@core/assist/RestletUtils.js"; 

export async function createInvestigation(request){
	return await restletUtils.restletPost("/docket/api/investigation",request,{});
}

export async function findInvestigationContext(id){
	return await restletUtils.restletGet("/docket/api/investigation/"+id);
}

