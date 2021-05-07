from .. import render_template, redirect, url_for
from ..forms.SupportForm import SupportForm

class SupportRoute():

    def getRenderTemplate():
        form = SupportForm()
        if form.validate_on_submit():
            try:
                return redirect(url_for('main.home'))
            except:
                return 'There was an issue submitting the form'
        return render_template('support.html', form=form)