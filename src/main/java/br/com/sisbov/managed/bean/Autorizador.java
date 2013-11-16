package br.com.sisbov.managed.bean;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class Autorizador<NavigationHandler> implements PhaseListener {

    private static final long serialVersionUID = 1L;

    public void afterPhase(PhaseEvent event) {
	FacesContext context = event.getFacesContext();
	if ("/login.xhtml".equals(context.getViewRoot().getViewId())) {
	    return;
	}

	UsuarioBean usuarioBean = context.getApplication()
		.evaluateExpressionGet(context, "#{usuarioBean}",
			UsuarioBean.class);

	if (!usuarioBean.isLogado()) {
	    NavigationHandler handler = (NavigationHandler) context.getApplication()
		    .getNavigationHandler();
	    ((javax.faces.application.NavigationHandler) handler).handleNavigation(context, null, "login?faces-redirect=true");
	    context.renderResponse();
	}

    }

    @Override
    public void beforePhase(PhaseEvent event) {
	// TODO Auto-generated method stub

    }

    @Override
    public PhaseId getPhaseId() {
	return PhaseId.RESTORE_VIEW;
    }

}

