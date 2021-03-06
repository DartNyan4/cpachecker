let EC = protractor.ExpectedConditions;

var hasClass = function (element, cls) {
    return element.getAttribute('class').then(function (classes) {
        console.log('')
        return classes.split(' ').indexOf(cls) !== -1;
    });
};

describe('Error path section in Report.js', function () {
    var dirname = __dirname + '/Counterexample.1.html';
    dirname = dirname.replace(/\\/g, "/");
    browser.waitForAngularEnabled(false);
    browser.get(dirname);
    browser.driver.sleep(100);

    describe('Start button click test in CFA tab', function () {


        it('Clicked Error path element in error path code', function () {
            element(by.css('#errorpath_section > header > div.btn-group > button.btn.btn-warning')).click();
            browser.wait(EC.presenceOf(element(by.css('.clickedErrPathElement'))));
            expect(hasClass(element(by.id('errpath-0')), 'clickedErrPathElement')).toBe(true);
        });

        it('Mark Error path element in CFA graph', function () {
            browser.wait(EC.presenceOf(element(by.css('.marked-cfa-edge'))));
            expect(hasClass(element(by.xpath('//*[@id="cfa-edge_23-27"]')), 'marked-cfa-edge')).toBe(true);
        })
    });

    describe('Next button click test in CFA tab', function () {

        it('Clicked Error path element in error path code', function () {
            element(by.css('#errorpath_section > header > div.btn-group > button:nth-child(3)')).click();
            browser.wait(EC.presenceOf(element(by.css('.clickedErrPathElement'))));
            expect(hasClass(element(by.id('errpath-1')), 'clickedErrPathElement')).toBe(true);
        });

        it('Mark Error path element in CFA graph', function () {
            browser.wait(EC.presenceOf(element(by.css('.marked-cfa-node'))));
            expect(hasClass(element(by.xpath('//*[@id="cfa-node27"]')), 'marked-cfa-node')).toBe(true);
        })

        it('Mark Error path element node label in CFA graph', function () {
            browser.wait(EC.presenceOf(element(by.css('.marked-cfa-node-label'))));
            expect(hasClass(element(by.css('#cfa-node27 > g > g > text > tspan.marked-cfa-node-label')), 'marked-cfa-node-label')).toBe(true);
        })
    });

    describe('Previous button click test in CFA tab', function () {


        it('Clicked Error path element in error path code', function () {
            element(by.css('#errorpath_section > header > div.btn-group > button:nth-child(1)')).click();
            browser.wait(EC.presenceOf(element(by.css('.clickedErrPathElement'))));
            expect(hasClass(element(by.id('errpath-0')), 'clickedErrPathElement')).toBe(true);
        });

        it('Mark Error path element in CFA graph', function () {
            browser.wait(EC.presenceOf(element(by.css('.marked-cfa-edge'))));
            expect(hasClass(element(by.xpath('//*[@id="cfa-edge_23-27"]')), 'marked-cfa-edge')).toBe(true);
        })
    });

    describe('Error Code line click test in CFA tab', function () {


        it('Clicked Error path element in error path code', function () {
            element(by.css('#errpath-6')).click();
            browser.wait(EC.presenceOf(element(by.css('.clickedErrPathElement'))));
            expect(hasClass(element(by.id('errpath-6')), 'clickedErrPathElement')).toBe(true);
        });

        it('Mark Error path element in CFA graph', function () {
            element(by.css('#errpath-6')).click();
            browser.wait(EC.presenceOf(element(by.css('.marked-cfa-node'))));
            expect(hasClass(element(by.xpath('//*[@id="cfa-node1"]')), 'marked-cfa-node')).toBe(true);
        })
    });

    describe('Start button click test in ARG tab', function () {


        it('Clicked Error path element in error path code', function () {
            element(by.id('set-tab-2')).click();
            element(by.css('#errorpath_section > header > div.btn-group > button.btn.btn-warning')).click();
            browser.wait(EC.presenceOf(element(by.css('.clickedErrPathElement'))));
            expect(hasClass(element(by.id('errpath-0')), 'clickedErrPathElement')).toBe(true);
        });

        it('Mark Error path element in ARG graph', function () {
            browser.wait(EC.presenceOf(element(by.css('.marked-arg-node'))));
            expect(hasClass(element(by.xpath('//*[@id="arg-node0"]')), 'marked-arg-node')).toBe(true);
        })
    });


    describe('Next button click test in ARG tab', function () {


        it('Clicked Error path element in error path code', function () {
            element(by.css('#errorpath_section > header > div.btn-group > button:nth-child(3)')).click();
            browser.wait(EC.presenceOf(element(by.css('.clickedErrPathElement'))));
            expect(hasClass(element(by.id('errpath-1')), 'clickedErrPathElement')).toBe(true);
        });

        it('Mark Error path element in ARG graph', function () {
            browser.wait(EC.presenceOf(element(by.css('.marked-arg-node'))));
            expect(hasClass(element(by.xpath('//*[@id="arg-node1"]')), 'marked-arg-node')).toBe(true);
        })

    });

    describe('Previous button click test in ARG tab', function () {


        it('Clicked Error path element in error path code', function () {
            element(by.css('#errorpath_section > header > div.btn-group > button:nth-child(1)')).click();
            browser.wait(EC.presenceOf(element(by.css('.clickedErrPathElement'))));
            expect(hasClass(element(by.id('errpath-0')), 'clickedErrPathElement')).toBe(true);
        });

        it('Mark Error path element in ARG graph', function () {
            browser.wait(EC.presenceOf(element(by.css('.marked-arg-node'))));
            expect(hasClass(element(by.xpath('//*[@id="arg-node0"]')), 'marked-arg-node')).toBe(true);
        })
    });

    describe('Error Code line click test in ARG tab', function () {


        it('Clicked Error path element in error path code', function () {
            element(by.css('#errpath-8')).click();
            browser.wait(EC.presenceOf(element(by.css('.clickedErrPathElement'))));
            expect(hasClass(element(by.id('errpath-8')), 'clickedErrPathElement')).toBe(true);
        });

        it('Mark Error path element in ARG graph', function () {
            element(by.css('#errpath-8')).click();
            browser.wait(EC.presenceOf(element(by.css('.marked-arg-node'))));
            expect(hasClass(element(by.xpath('//*[@id="arg-node4"]')), 'marked-arg-node')).toBe(true);
        })
    });

    describe('Start button click test in Source tab', function () {


        it('Clicked Error path element in error path code', function () {
            element(by.id('set-tab-3')).click();
            element(by.css('#errorpath_section > header > div.btn-group > button.btn.btn-warning')).click();
            browser.wait(EC.presenceOf(element(by.css('.clickedErrPathElement'))));
            expect(hasClass(element(by.id('errpath-0')), 'clickedErrPathElement')).toBe(true);
        });

        it('Highlight selected code in source tab', function () {
            browser.wait(EC.presenceOf(element(by.css('.marked-source-line'))));
            expect(hasClass(element(by.xpath('//*[@id="source-1"]/td[2]/pre')), 'marked-source-line')).toBe(true);
        })
    });

    describe('Next button click test in Source tab', function () {


        it('Clicked Error path element in error path code', function () {
            element(by.css('#errorpath_section > header > div.btn-group > button:nth-child(3)')).click();
            browser.wait(EC.presenceOf(element(by.css('.clickedErrPathElement'))));
            element(by.css('#errorpath_section > header > div.btn-group > button:nth-child(3)')).click();
            browser.wait(EC.presenceOf(element(by.css('.clickedErrPathElement'))));
            expect(hasClass(element(by.id('errpath-2')), 'clickedErrPathElement')).toBe(true);
        });

        it('Highlight selected code in source tab', function () {
            browser.wait(EC.presenceOf(element(by.css('.marked-source-line'))));
            expect(hasClass(element(by.xpath('//*[@id="source-2"]/td[2]/pre')), 'marked-source-line')).toBe(true);
        })

    });

    describe('Previous button click test in Source tab', function () {


        it('Clicked Error path element in error path code', function () {
            element(by.css('#errorpath_section > header > div.btn-group > button:nth-child(1)')).click();
            browser.wait(EC.presenceOf(element(by.css('.clickedErrPathElement'))));
            element(by.css('#errorpath_section > header > div.btn-group > button:nth-child(1)')).click();
            browser.wait(EC.presenceOf(element(by.css('.clickedErrPathElement'))));
            expect(hasClass(element(by.id('errpath-0')), 'clickedErrPathElement')).toBe(true);
        });

        it('Highlight selected code in source tab', function () {
            browser.wait(EC.presenceOf(element(by.css('.marked-source-line'))));
            expect(hasClass(element(by.xpath('//*[@id="source-1"]/td[2]/pre')), 'marked-source-line')).toBe(true);
        })
    });


    // it('Show values of Error Code line', function () {
    //     element(by.css('#errpath-6 > td:nth-child(1)')).click();
    //     browser.wait(EC.presenceOf(element(by.css('#report-controller > div.popover.fade.show.bs-popover-left'))));
    //     // element(by.xpath('//*[@id="errpath-0"]')).getAttribute('class').then(function (classes) {
    //     //     console.log(classes);
    //     //     // return classes.split(' ').indexOf(cls) !== -1;
    //     // });

    //     it('Display popover with values of line in error path code', function () {
    //         expect(element(by.css('#report-controller > div.popover.fade.show.bs-popover-left')).isDisplayed()).toBeTruthy();
    //     });
    //     browser.actions().mouseMove(element(by.xpath('//*[@id="errpath-6"]/td[1]'))).click();
    // });

    it('Prev button tooltip test', function () {
        tooltipText = element(by.xpath('//div[@class="tooltip-inner"]'));
        browser.actions().mouseMove(element(by.xpath('//*[@id="errorpath_section"]/header/div[1]/button[1]'))).perform();
        browser.wait(EC.presenceOf(tooltipText))
        expect(tooltipText.isDisplayed()).toBeTruthy();
    });
    browser.driver.sleep(100);

    it('help button tooltip test', function () {
        tooltipText = element(by.xpath('//div[@class="tooltip-inner"]'));
        browser.actions().mouseMove(element(by.xpath('//*[@id="errorpath_section"]/header/div[2]'))).perform();
        browser.wait(EC.presenceOf(tooltipText))
        expect(tooltipText.isDisplayed()).toBeTruthy();
    });

    it('Next button tooltip test', function () {
        tooltipText = element(by.xpath('//div[@class="tooltip-inner"]'));
        browser.actions().mouseMove(element(by.xpath('//*[@id="errorpath_section"]/header/div[1]/button[3]'))).perform();
        browser.wait(EC.presenceOf(tooltipText))
        expect(tooltipText.isDisplayed()).toBeTruthy();
    });

});