package com.growingitskill.controller;

import java.io.IOException;
import java.net.URI;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.growingitskill.config.WebAppInitializer;
import com.growingitskill.domain.AttachmentVO;
import com.growingitskill.domain.BlogInfo;
import com.growingitskill.service.AboutService;
import com.growingitskill.service.AttachmentService;
import com.growingitskill.service.BlogInfoService;
import com.growingitskill.util.MemberUtils;
import com.growingitskill.util.UploadFileUtils;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AboutService aboutService;

	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private BlogInfoService blogInfoService;
	
	@Autowired
	private MemberUtils memberUtils;

	@RequestMapping(method = RequestMethod.GET)
	public String moveIndex(Model model, Principal principal) throws Exception {
		memberUtils.makeMemberModel(model, principal.getName());

		return "admin/main";
	}

	@RequestMapping(value = "category", method = RequestMethod.GET)
	public String moveCategory(Model model, Principal principal) throws Exception {
		memberUtils.makeMemberModel(model, principal.getName());
		
		return "admin/category";
	}

	@RequestMapping(value = "tag", method = RequestMethod.GET)
	public String moveTag(Model model, Principal principal) throws Exception {
		memberUtils.makeMemberModel(model, principal.getName());
		
		return "admin/tag";
	}

	@RequestMapping(value = "media", method = RequestMethod.GET)
	public String moveMedia(Model model, Principal principal) throws Exception {
		memberUtils.makeMemberModel(model, principal.getName());
		
		model.addAttribute("list", attachmentService.listAll());

		return "admin/media";
	}

	@RequestMapping(value = "about-edit", method = RequestMethod.GET)
	public String moveAboutEdit(Model model, Principal principal) throws Exception {
		memberUtils.makeMemberModel(model, principal.getName());
		
		model.addAttribute("content", aboutService.findAbout());

		return "admin/about-edit";
	}

	@RequestMapping(value = "about-edit", method = RequestMethod.PUT)
	public String modifyContentInAboutEdit(String content, RedirectAttributes redirectAttributes) throws Exception {
		aboutService.modifyAboutContent(content);

		redirectAttributes.addFlashAttribute("msg", "success");

		return "redirect:/admin/about-edit";
	}
	
	@RequestMapping(value = "blog-info", method = RequestMethod.GET)
	public String moveBlogInfo(Model model) throws Exception {
		model.addAttribute("blogInfo", blogInfoService.findBlogInfo());
		
		return "admin/blog-info";
	}
	
	@RequestMapping(value = "blog-info", method = RequestMethod.PUT)
	public String modifyBlogInfo(BlogInfo blogInfo, RedirectAttributes redirectAttributes) throws Exception {
		blogInfoService.modifyBlogInfo(blogInfo);
		
		redirectAttributes.addFlashAttribute("msg", "success");
		
		return "redirect:/admin/blog-info";
	}
	
	/**
	 * 
	 * @param upload
	 *            CKeditor의 파일 업로드는 Request Payload의 name에 따라 MultipartFile
	 *            변수이름을 "upload"라 해준다. (다른 이름일 시 파일을 못 찾는 버그 발생)
	 * @throws Exception
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String uploadByCKEditor(@RequestPart("upload") MultipartFile file,
			@RequestParam("CKEditorFuncNum") int number, UriComponentsBuilder uriComponentsBuilder, Model model,
			HttpServletResponse response) throws Exception {
		printUploadFileInfo(file);
		LOGGER.info("number: " + number);

		String path = WebAppInitializer.uploadPath;

		String fullName = UploadFileUtils.uploadFile(path, file.getOriginalFilename(), file.getBytes());

		AttachmentVO attachmentVO = new AttachmentVO();
		attachmentVO.setFullName(fullName);
		attachmentVO.setMimeType(file.getContentType());

		attachmentService.addAttachment(attachmentVO);

		URI locationUri = uriComponentsBuilder.path("/attachments/").path(String.valueOf(attachmentVO.getId())).build()
				.toUri();

		response.setHeader("Location", locationUri.toString());

		model.addAttribute("CKEditorFuncNum", number);
		model.addAttribute("fileUrl", "/resources/upload" + fullName);

		return "admin/upload";
	}
	
	private void printUploadFileInfo(MultipartFile file) throws IOException {
		LOGGER.info("originalFilename: " + file.getOriginalFilename());
		LOGGER.info("contentType: " + file.getContentType());
		LOGGER.info("name: " + file.getName());
		LOGGER.info("byte: " + file.getBytes());
		LOGGER.info("size: " + file.getSize());
	}

}
