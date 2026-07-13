<%@ include file="/init.jsp" %>

<%-- 1. Securely generate the target Portlet action URL --%>
<portlet:actionURL name="/ai/upload_pdf" var="uploadPDFURL" />

<div class="card">
    <div class="card-body">

        <h3>Upload PDF</h3>

        <%-- 2. FIXED: Properly injected the generated URL variable into the action attribute --%>
        <form action="${uploadPDFURL}" method="post" enctype="multipart/form-data">

            <div class="form-group">
                <label for="pdfFile">Select PDF</label>
                
                <%-- 3. File input with Bootstrap styling and portlet namespacing --%>
                <input
                    id="pdfFile"
                    type="file"
                    name="<portlet:namespace />pdfFile"
                    accept=".pdf"
                    class="form-control"
                    required />
            </div>

            <br/>

            <%-- 4. Styled submit button --%>
            <button type="submit" class="btn btn-primary">
                Upload PDF
            </button>

        </form>

    </div>
</div>