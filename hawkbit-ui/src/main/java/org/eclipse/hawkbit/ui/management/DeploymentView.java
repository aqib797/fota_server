/**
 * Copyright (c) 2015 Bosch Software Innovations GmbH and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.hawkbit.ui.management;

import java.util.Map;
import java.util.concurrent.Executor;

import javax.annotation.PostConstruct;

import org.eclipse.hawkbit.repository.DeploymentManagement;
import org.eclipse.hawkbit.repository.DistributionSetManagement;
import org.eclipse.hawkbit.repository.DistributionSetTagManagement;
import org.eclipse.hawkbit.repository.DistributionSetTypeManagement;
import org.eclipse.hawkbit.repository.EntityFactory;
import org.eclipse.hawkbit.repository.SystemManagement;
import org.eclipse.hawkbit.repository.TargetFilterQueryManagement;
import org.eclipse.hawkbit.repository.TargetManagement;
import org.eclipse.hawkbit.repository.TargetTagManagement;
import org.eclipse.hawkbit.ui.AbstractHawkbitUI;
import org.eclipse.hawkbit.ui.SpPermissionChecker;
import org.eclipse.hawkbit.ui.UiProperties;
import org.eclipse.hawkbit.ui.common.table.BaseEntityEventType;
import org.eclipse.hawkbit.ui.components.AbstractNotificationView;
import org.eclipse.hawkbit.ui.components.NotificationUnreadButton;
import org.eclipse.hawkbit.ui.components.RefreshableContainer;
import org.eclipse.hawkbit.ui.dd.criteria.ManagementViewClientCriterion;
import org.eclipse.hawkbit.ui.management.actionhistory.ActionHistoryLayout;
import org.eclipse.hawkbit.ui.management.actionhistory.ActionStatusGrid;
import org.eclipse.hawkbit.ui.management.actionhistory.ActionStatusLayout;
import org.eclipse.hawkbit.ui.management.actionhistory.ActionStatusMsgGrid;
import org.eclipse.hawkbit.ui.management.actionhistory.ActionStatusMsgLayout;
import org.eclipse.hawkbit.ui.management.dstable.DistributionTableLayout;
import org.eclipse.hawkbit.ui.management.dstag.DistributionTagLayout;
import org.eclipse.hawkbit.ui.management.event.DistributionTableEvent;
import org.eclipse.hawkbit.ui.management.event.ManagementUIEvent;
import org.eclipse.hawkbit.ui.management.event.TargetTableEvent;
import org.eclipse.hawkbit.ui.management.footer.DeleteActionsLayout;
import org.eclipse.hawkbit.ui.management.state.DistributionTableFilters;
import org.eclipse.hawkbit.ui.management.state.ManagementUIState;
import org.eclipse.hawkbit.ui.management.targettable.TargetTable;
import org.eclipse.hawkbit.ui.management.targettable.TargetTableLayout;
import org.eclipse.hawkbit.ui.management.targettag.CreateUpdateTargetTagLayoutWindow;
import org.eclipse.hawkbit.ui.management.targettag.TargetTagFilterLayout;
import org.eclipse.hawkbit.ui.menu.DashboardMenuItem;
import org.eclipse.hawkbit.ui.push.DistributionSetCreatedEventContainer;
import org.eclipse.hawkbit.ui.push.DistributionSetDeletedEventContainer;
import org.eclipse.hawkbit.ui.push.DistributionSetTagCreatedEventContainer;
import org.eclipse.hawkbit.ui.push.DistributionSetTagDeletedEventContainer;
import org.eclipse.hawkbit.ui.push.DistributionSetTagUpdatedEventContainer;
import org.eclipse.hawkbit.ui.push.TargetCreatedEventContainer;
import org.eclipse.hawkbit.ui.push.TargetDeletedEventContainer;
import org.eclipse.hawkbit.ui.push.TargetTagCreatedEventContainer;
import org.eclipse.hawkbit.ui.push.TargetTagDeletedEventContainer;
import org.eclipse.hawkbit.ui.push.TargetTagUpdatedEventContainer;
import org.eclipse.hawkbit.ui.utils.SPUIDefinitions;
import org.eclipse.hawkbit.ui.utils.UINotification;
import org.eclipse.hawkbit.ui.utils.VaadinMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.vaadin.spring.events.EventBus.UIEventBus;
import org.vaadin.spring.events.EventScope;
import org.vaadin.spring.events.annotation.EventBusListenerMethod;

import com.google.common.collect.Maps;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.Page.BrowserWindowResizeEvent;
import com.vaadin.server.Page.BrowserWindowResizeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;

/**
 * Target status and deployment management view
 */
@UIScope
@SpringView(name = DeploymentView.VIEW_NAME, ui = AbstractHawkbitUI.class)
public class DeploymentView extends AbstractNotificationView implements BrowserWindowResizeListener {

    private static final long serialVersionUID = 1L;

    public static final String VIEW_NAME = "deployment";

    private final SpPermissionChecker permChecker;

    private final VaadinMessageSource i18n;

    private final UINotification uiNotification;

    private final ManagementUIState managementUIState;

    private final ActionHistoryLayout actionHistoryLayout;

    private final ActionStatusLayout actionStatusLayout;

    private final ActionStatusMsgLayout actionStatusMsgLayout;

    private final TargetTagFilterLayout targetTagFilterLayout;

    private final TargetTableLayout targetTableLayout;

    private final DistributionTagLayout distributionTagLayout;

    private final DistributionTableLayout distributionTableLayout;

    private final DeleteActionsLayout deleteAndActionsLayout;

    private GridLayout mainLayout;

    private final DeploymentViewMenuItem deploymentViewMenuItem;

    @Autowired
    DeploymentView(final UIEventBus eventbus, final SpPermissionChecker permChecker, final VaadinMessageSource i18n,
            final UINotification uiNotification, final ManagementUIState managementUIState,
            final DeploymentManagement deploymentManagement, final UIEventBus eventBus,
            final DistributionTableFilters distFilterParameters,
            final DistributionSetManagement distributionSetManagement,
            final DistributionSetTypeManagement distributionSetTypeManagement, final TargetManagement targetManagement,
            final EntityFactory entityFactory, final UiProperties uiproperties,
            final ManagementViewClientCriterion managementViewClientCriterion,
            final TargetTagManagement targetTagManagement,
            final DistributionSetTagManagement distributionSetTagManagement,
            final TargetFilterQueryManagement targetFilterQueryManagement, final SystemManagement systemManagement,
            final NotificationUnreadButton notificationUnreadButton,
            final DeploymentViewMenuItem deploymentViewMenuItem, @Qualifier("uiExecutor") final Executor uiExecutor) {
        super(eventBus, notificationUnreadButton);
        this.permChecker = permChecker;
        this.i18n = i18n;
        this.uiNotification = uiNotification;
        this.managementUIState = managementUIState;

        this.deploymentViewMenuItem = deploymentViewMenuItem;

        if (permChecker.hasTargetReadPermission()) {
            this.actionHistoryLayout = new ActionHistoryLayout(i18n, deploymentManagement, eventBus, uiNotification,
                    managementUIState);
            this.actionStatusLayout = new ActionStatusLayout(i18n, eventBus, managementUIState);
            this.actionStatusMsgLayout = new ActionStatusMsgLayout(i18n, eventBus, managementUIState);
            final CreateUpdateTargetTagLayoutWindow createUpdateTargetTagLayout = new CreateUpdateTargetTagLayoutWindow(
                    i18n, targetTagManagement, entityFactory, eventBus, permChecker, uiNotification);
            this.targetTagFilterLayout = new TargetTagFilterLayout(i18n, createUpdateTargetTagLayout, managementUIState,
                    managementViewClientCriterion, permChecker, eventBus, uiNotification, entityFactory,
                    targetFilterQueryManagement);
            final TargetTable targetTable = new TargetTable(eventBus, i18n, uiNotification, targetManagement,
                    managementUIState, permChecker, managementViewClientCriterion, distributionSetManagement,
                    targetTagManagement);

            this.targetTableLayout = new TargetTableLayout(eventbus, targetTable, targetManagement, entityFactory, i18n,
                    eventBus, uiNotification, managementUIState, managementViewClientCriterion, deploymentManagement,
                    uiproperties, permChecker, uiNotification, targetTagManagement, distributionSetManagement,
                    uiExecutor);
            this.deleteAndActionsLayout = new DeleteActionsLayout(i18n, permChecker, eventBus, uiNotification,
                    targetTagManagement, distributionSetTagManagement, managementViewClientCriterion, managementUIState,
                    targetManagement, targetTable, deploymentManagement, distributionSetManagement);

            actionHistoryLayout.registerDetails(((ActionStatusGrid) actionStatusLayout.getGrid()).getDetailsSupport());
            actionStatusLayout
                    .registerDetails(((ActionStatusMsgGrid) actionStatusMsgLayout.getGrid()).getDetailsSupport());
        } else {
            this.actionHistoryLayout = null;
            this.actionStatusLayout = null;
            this.actionStatusMsgLayout = null;
            this.targetTagFilterLayout = null;
            this.targetTableLayout = null;
            this.deleteAndActionsLayout = null;
        }

        if (permChecker.hasReadRepositoryPermission()) {
            this.distributionTagLayout = new DistributionTagLayout(eventbus, managementUIState, i18n, permChecker,
                    eventBus, distributionSetTagManagement, entityFactory, uiNotification, distFilterParameters,
                    distributionSetManagement, managementViewClientCriterion);
            this.distributionTableLayout = new DistributionTableLayout(i18n, eventBus, permChecker, managementUIState,
                    distributionSetManagement, distributionSetTypeManagement, managementViewClientCriterion,
                    entityFactory, uiNotification, distributionSetTagManagement, targetTagManagement, systemManagement,
                    targetManagement, deploymentManagement);
        } else {
            this.distributionTagLayout = null;
            this.distributionTableLayout = null;
        }

    }

    @PostConstruct
    void init() {
        buildLayout();
        restoreState();
        checkNoDataAvaialble();
        Page.getCurrent().addBrowserWindowResizeListener(this);
        showOrHideFilterButtons(Page.getCurrent().getBrowserWindowWidth());
        getEventBus().publish(this, ManagementUIEvent.SHOW_COUNT_MESSAGE);
    }

    @Override
    public void enter(final ViewChangeEvent event) {
        if (permChecker.hasReadRepositoryPermission()) {
            distributionTableLayout.getDistributionTable().selectEntity(managementUIState.getLastSelectedDsIdName());
        }
    }

    @Override
    protected DashboardMenuItem getDashboardMenuItem() {
        return deploymentViewMenuItem;
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    void onEvent(final DistributionTableEvent event) {
        if (BaseEntityEventType.MINIMIZED == event.getEventType()) {
            minimizeDistTable();
        } else if (BaseEntityEventType.MAXIMIZED == event.getEventType()) {
            maximizeDistTable();
        }
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    void onEvent(final TargetTableEvent event) {
        if (BaseEntityEventType.MINIMIZED == event.getEventType()) {
            minimizeTargetTable();
        } else if (BaseEntityEventType.MAXIMIZED == event.getEventType()) {
            maximizeTargetTable();
        }
    }

    @EventBusListenerMethod(scope = EventScope.UI)
    void onEvent(final ManagementUIEvent mgmtUIEvent) {
        if (mgmtUIEvent == ManagementUIEvent.MAX_ACTION_HISTORY) {
            UI.getCurrent().access(this::maximizeActionHistory);
        }
        if (mgmtUIEvent == ManagementUIEvent.MIN_ACTION_HISTORY) {
            UI.getCurrent().access(this::minimizeActionHistory);
        }
    }

    private void restoreState() {
        if (managementUIState.isTargetTableMaximized()) {
            maximizeTargetTable();
        }
        if (managementUIState.isDsTableMaximized()) {
            maximizeDistTable();
        }
        if (managementUIState.isActionHistoryMaximized()) {
            maximizeActionHistory();
        }
    }

    private void buildLayout() {
        // Build only if user has both permissions
        if (permChecker.hasTargetReadPermission() || permChecker.hasReadRepositoryPermission()) {
            setSizeFull();
            createMainLayout();
            addComponents(mainLayout);
            setExpandRatio(mainLayout, 1);
        }
    }

    private void createMainLayout() {
        mainLayout = new GridLayout();
        layoutWidgets();
        mainLayout.setSizeFull();
        mainLayout.setSpacing(true);
        mainLayout.setRowExpandRatio(0, 1F);
    }

    private void layoutWidgets() {
        mainLayout.removeAllComponents();
        if (permChecker.hasReadRepositoryPermission() && permChecker.hasTargetReadPermission()) {
            displayAllWidgets();
        } else if (permChecker.hasReadRepositoryPermission()) {
            displayDistributionWidgetsOnly();
        } else if (permChecker.hasTargetReadPermission()) {
            displayTargetWidgetsOnly();
        }
    }

    private void displayAllWidgets() {
        mainLayout.setColumns(5);
        mainLayout.setRows(2);
        mainLayout.addComponent(targetTagFilterLayout, 0, 0);
        mainLayout.addComponent(targetTableLayout, 1, 0);
        mainLayout.addComponent(distributionTableLayout, 2, 0);
        mainLayout.addComponent(distributionTagLayout, 3, 0);
        mainLayout.addComponent(actionHistoryLayout, 4, 0);
        mainLayout.setColumnExpandRatio(0, 0F);
        mainLayout.setColumnExpandRatio(1, 0.275F);
        mainLayout.setColumnExpandRatio(2, 0.275F);
        mainLayout.setColumnExpandRatio(3, 0F);
        mainLayout.setColumnExpandRatio(4, 0.45F);
        if (showFooterLayout()) {
            mainLayout.addComponent(deleteAndActionsLayout, 1, 1, 2, 1);
            mainLayout.setComponentAlignment(deleteAndActionsLayout, Alignment.BOTTOM_CENTER);
        }
    }

    private void displayDistributionWidgetsOnly() {
        mainLayout.setColumns(2);
        mainLayout.setRows(2);
        mainLayout.addComponent(distributionTableLayout, 0, 0);
        mainLayout.addComponent(distributionTagLayout, 1, 0);
        mainLayout.setColumnExpandRatio(0, 1F);
        if (showFooterLayout()) {
            mainLayout.addComponent(deleteAndActionsLayout, 0, 1);
            mainLayout.setComponentAlignment(deleteAndActionsLayout, Alignment.BOTTOM_CENTER);
        }
    }

    private Boolean showFooterLayout() {
        if (permChecker.hasTargetReadPermission()
                || (permChecker.hasDeleteRepositoryPermission() || permChecker.hasDeleteTargetPermission())
                || hasDeploymentPermission()) {
            return true;
        }
        return false;
    }

    private boolean hasDeploymentPermission() {
        return permChecker.hasReadRepositoryPermission() && permChecker.hasUpdateTargetPermission();
    }

    private void displayTargetWidgetsOnly() {
        mainLayout.setColumns(3);
        mainLayout.setRows(2);
        mainLayout.addComponent(targetTagFilterLayout, 0, 0);
        mainLayout.addComponent(targetTableLayout, 1, 0);
        mainLayout.addComponent(actionHistoryLayout, 2, 0);
        mainLayout.setColumnExpandRatio(1, 0.4F);
        mainLayout.setColumnExpandRatio(2, 0.6F);
        if (showFooterLayout()) {
            mainLayout.addComponent(deleteAndActionsLayout, 1, 1);
            mainLayout.setComponentAlignment(deleteAndActionsLayout, Alignment.BOTTOM_CENTER);
        }
    }

    private void maximizeTargetTable() {
        if (permChecker.hasReadRepositoryPermission()) {
            mainLayout.removeComponent(distributionTableLayout);
            mainLayout.removeComponent(distributionTagLayout);
        }
        mainLayout.removeComponent(actionHistoryLayout);
        mainLayout.removeComponent(deleteAndActionsLayout);
        mainLayout.setColumnExpandRatio(1, 1F);
        mainLayout.setColumnExpandRatio(2, 0F);
        mainLayout.setColumnExpandRatio(3, 0F);
        mainLayout.setColumnExpandRatio(4, 0F);
    }

    private void maximizeDistTable() {
        if (permChecker.hasTargetReadPermission()) {
            mainLayout.removeComponent(targetTagFilterLayout);
            mainLayout.removeComponent(targetTableLayout);
            mainLayout.removeComponent(actionHistoryLayout);
        }
        mainLayout.removeComponent(deleteAndActionsLayout);
        mainLayout.setColumnExpandRatio(0, 0F);
        mainLayout.setColumnExpandRatio(1, 0F);
        mainLayout.setColumnExpandRatio(2, 1F);
        mainLayout.setColumnExpandRatio(4, 0F);
    }

    private void maximizeActionHistory() {
        mainLayout.removeAllComponents();
        mainLayout.setColumns(3);
        mainLayout.setRows(1);
        mainLayout.addComponent(actionHistoryLayout, 0, 0);
        mainLayout.addComponent(actionStatusLayout, 1, 0);
        mainLayout.addComponent(actionStatusMsgLayout, 2, 0);
        mainLayout.setColumnExpandRatio(0, 0.55F);
        mainLayout.setColumnExpandRatio(1, 0.18F);
        mainLayout.setColumnExpandRatio(2, 0.27F);
        mainLayout.setComponentAlignment(actionHistoryLayout, Alignment.TOP_LEFT);
    }

    private void minimizeTargetTable() {
        layoutWidgets();
    }

    private void minimizeDistTable() {
        layoutWidgets();
    }

    private void minimizeActionHistory() {
        layoutWidgets();
    }

    private void checkNoDataAvaialble() {
        if (managementUIState.isNoDataAvilableTarget() && managementUIState.isNoDataAvailableDistribution()) {
            uiNotification.displayValidationError(i18n.getMessage("message.no.data"));
        }
    }

    @Override
    public void browserWindowResized(final BrowserWindowResizeEvent event) {
        final int browserWidth = event.getWidth();
        showOrHideFilterButtons(browserWidth);
    }

    private void showOrHideFilterButtons(final int browserWidth) {
        if (browserWidth < SPUIDefinitions.REQ_MIN_BROWSER_WIDTH) {
            if (permChecker.hasTargetReadPermission()) {
                targetTagFilterLayout.setVisible(false);
                targetTableLayout.setShowFilterButtonVisible(true);
            }

            if (permChecker.hasReadRepositoryPermission()) {
                distributionTagLayout.setVisible(false);
                distributionTableLayout.setShowFilterButtonVisible(true);
            }
        } else {
            if (permChecker.hasTargetReadPermission() && !managementUIState.isTargetTagFilterClosed()) {
                targetTagFilterLayout.setVisible(true);
                targetTableLayout.setShowFilterButtonVisible(false);

            }
            if (permChecker.hasReadRepositoryPermission() && !managementUIState.isDistTagFilterClosed()) {
                distributionTagLayout.setVisible(true);
                distributionTableLayout.setShowFilterButtonVisible(false);
            }
        }
    }

    @Override
    protected Map<Class<?>, RefreshableContainer> getSupportedPushEvents() {
        final Map<Class<?>, RefreshableContainer> supportedEvents = Maps.newHashMapWithExpectedSize(10);

        supportedEvents.put(TargetCreatedEventContainer.class, targetTableLayout.getTable());
        supportedEvents.put(TargetDeletedEventContainer.class, targetTableLayout.getTable());

        supportedEvents.put(DistributionSetCreatedEventContainer.class, distributionTableLayout.getTable());
        supportedEvents.put(DistributionSetDeletedEventContainer.class, distributionTableLayout.getTable());

        supportedEvents.put(TargetTagCreatedEventContainer.class, targetTagFilterLayout);
        supportedEvents.put(TargetTagDeletedEventContainer.class, targetTagFilterLayout);
        supportedEvents.put(TargetTagUpdatedEventContainer.class, targetTagFilterLayout);

        supportedEvents.put(DistributionSetTagCreatedEventContainer.class, distributionTagLayout);
        supportedEvents.put(DistributionSetTagDeletedEventContainer.class, distributionTagLayout);
        supportedEvents.put(DistributionSetTagUpdatedEventContainer.class, distributionTagLayout);

        return supportedEvents;
    }

}
