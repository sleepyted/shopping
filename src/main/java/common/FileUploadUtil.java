package common;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Attachment;
import bean.Result;
import com.google.gson.Gson;
import dao.AttachmentDao;
import dao.PubDefine;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadUtil implements PubDefine{
	private static final long serialVersionUID = 1L;
	private static final String TAG = "FileUploadUtil";

	// 上传文件存储目录
	private static final String UPLOAD_DIRECTORY = "upload";

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	public FileUploadUtil() {
		super();
	}

	public int doRecive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		// 检测是否为多媒体上传
		if (!ServletFileUpload.isMultipartContent(request)) {
			// 如果不是则停止

			Result result = new Result(0, "上传失败！类型错误");
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(result));
			return STATUS_ERROR;
		}

		//记住要写这句话，上传的文件名才不会乱码！！！！！！！！！
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
//		String uploadPath = request.getContextPath() + File.separator + UPLOAD_DIRECTORY;

		//获取项目发布路径  下的upload文件夹
		String	uploadPath = request.getSession().getServletContext().getRealPath("/upload");
//		uploadPath = "D://666//shopping//fileTmp";

		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}

		try {
			// 解析请求的内容提取文件数据
//                    @SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (!item.isFormField()) {
						//这里处理文件中文乱码没用。。。。。。。。。
						Log.i(TAG,new String(item.getName().getBytes("utf-8"), "iso-8859-1"));
						File uploadFile = new File(item.getName());
						String originName = uploadFile.getName();
						String localName = Util.getUUID();
						String ext = Util.getExtensionName(originName);
						localName = localName + ext;

						String filePath = uploadPath + File.separator + localName;
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						Log.i(TAG, filePath);
						// 保存文件到硬盘
						item.write(storeFile);

						//保存文件信息到数据库
						Attachment attachment = new Attachment();
						attachment.setOriginName(originName);
						attachment.setLocalName(localName);
						AttachmentDao dao = new AttachmentDao();
						int fileStoreId = dao.insert(attachment);

						if(fileStoreId != STATUS_ERROR) {
							Result result = new Result(1, String.valueOf(fileStoreId));
							Gson gson = new Gson();
							response.getWriter().write(gson.toJson(result));
							return fileStoreId;
						}else {
							Result result = new Result(0, "数据库异常！");
							Gson gson = new Gson();
							response.getWriter().write(gson.toJson(result));
							return STATUS_ERROR;
						}
					}
				}
			}
			Result result = new Result(0, "上传数据错误！");
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(result));
			return STATUS_ERROR;

		} catch (Exception ex) {
			Log.i(TAG, ex.toString());
			Result result = new Result(0, "上传失败！");
			Gson gson = new Gson();
			response.getWriter().write(gson.toJson(result));
			return STATUS_ERROR;
		}
		//request.getRequestDispatcher("/message.jsp").forward(request, response);
	}
}